import React from 'react'
import ReactDOM from 'react-dom'
import Enzyme, {mount} from "enzyme"
import Adapter from "enzyme-adapter-react-16"
import IconButton from '@material-ui/core/IconButton'

import App from './App'
import ApiClient from "./ApiClient"

Enzyme.configure({adapter: new Adapter()})

jest.mock("./ApiClient", () => {
  postMessage: jest.fn().mockImplementation(() => Promise.resolve())
})

describe('App', () => {
  describe('initial mount', () => {
    it('should focus input', () => {
      const subject = mount(<App/>)
      expect(subject.find('input').at(0).is(':focus')).toBeTruthy()
    })

    it('should make API call to retrieve messages', () => {
      // expect(ApiClient.getMessages).toHaveBeenCalled()
    })
  })

  it('when form is submitted, should make API call', () => {
    const subject = mount(<App/>)

    subject.find(IconButton).simulate('click')

    expect(ApiClient.postMessage).toHaveBeenCalledWith({'content': 'some message'})
  })
})
