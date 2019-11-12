import React from 'react'

import {IconButton, InputAdornment, OutlinedInput, Card} from '@material-ui/core'
import SendIcon from '@material-ui/icons/Send'

import './App.css'
import ApiClient from "./ApiClient"

export class App extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      content: "",
      messages: []
    }
  }

  componentDidMount() {
    this.getMessages()
  }

  async getMessages() {
    const response = await ApiClient.getMessages()
    this.setState({messages: response.data})
  }

  onChangeHandler = (event) => {
    this.setState({content: event.target.value})
  }

  submitMessage = async () => {
    await ApiClient.postMessage({'content': this.state.content})
    this.getMessages()
    this.setState({content: ''})
  }

  getMessageList() {
    let messageList = this.state.messages.map((message, index) =>
      <div key={index} className="message-list__message">
        <Card>
          <div className="message-list__content">{message.content}</div>
        </Card>
      </div>
    ).reverse()

    return messageList
  }

  render() {
    return (
      <div className="main">
        <div className="message-list">
          {this.getMessageList()}
        </div>
        <div className="form">
          <OutlinedInput type="text" onChange={this.onChangeHandler} data-cy="MessageTextField"
                         value={this.state.content} fullWidth={true}
                         endAdornment={
                           <InputAdornment>
                             <IconButton onClick={this.submitMessage} value="Submit">
                               <SendIcon/>
                             </IconButton>
                           </InputAdornment>
                         }
          />
        </div>
      </div>
    )
  }
}

export default App
