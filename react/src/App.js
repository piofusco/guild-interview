import React from 'react'

import IconButton from '@material-ui/core/IconButton'
import InputAdornment from "@material-ui/core/InputAdornment"
import OutlinedInput from "@material-ui/core/OutlinedInput"
import SendIcon from '@material-ui/icons/Send'

import './App.css'
import ApiClient from "./ApiClient"

export class App extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      content: ""
    }
  }

  onChangeHandler = (event) => {
    this.setState({"content": event.target.value})
  }

  submitMessage = (event) => {
    console.log("start here tomorrow?")
    const response = ApiClient.postMessage(this.state.content)
  }

  render() {
    return (
      <div className="form">
        <OutlinedInput id="outlined-adornment-password"
                       type="text"
                       onChange={this.onChangeHandler}
                       autoFocus={true}
                       endAdornment={
                         <InputAdornment>
                           <IconButton aria-label="send message"
                                       onClick={this.submitMessage}>
                             <SendIcon/>
                           </IconButton>
                         </InputAdornment>
                       }
                       fullWidth={true}/>
      </div>
    )
  }
}

export default App
