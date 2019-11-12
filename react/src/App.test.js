import React from 'react'
import ReactDOM from 'react-dom'
import Enzyme, {mount} from "enzyme"
import Adapter from "enzyme-adapter-react-16"
import IconButton from '@material-ui/core/IconButton'

import App from './App'
import ApiClient from "./ApiClient"

Enzyme.configure({adapter: new Adapter()})

jest.mock("./ApiClient", () => ({
  postMessage: jest.fn().mockImplementation(() => Promise.resolve({data: 'data'}).then()),
  getMessages: jest.fn().mockImplementation(() => new Promise.resolve((resolve) => resolve(
    {
      data: [
        {'content': 'first message'},
        {'content': 'second message'}
      ]
    }
  )))
}))

describe('App', () => {
  beforeEach(() => {
    jest.clearAllMocks()
  })

  describe('initial mount', () => {
    it('should focus input', () => {
      const subject = mount(<App/>)

      expect(subject.find('input').at(0).is(':focus')).toBeTruthy()
    })

    it('should make API call to retrieve messages', () => {
      const subject = mount(<App/>)

      expect(ApiClient.getMessages).toHaveBeenCalled()
    })
  })

  it('when icon button is clicked, should make API call with content of input, and clear state', () => {
    const subject = mount(<App/>)
    subject.state().content = 'some message'

    subject.find(IconButton).simulate('click')

    expect(ApiClient.postMessage).toHaveBeenCalledWith({'content': 'some message'})
    expect(subject.state().content).toEqual('')
  })
})
