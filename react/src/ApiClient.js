import axios from 'axios'

class ApiClient {
  static async getMessages() {
    return await axios.get('/api/v1/messages')
  }

  static async postMessage(message) {
    console.log("sup")
    return await axios.post('/api/v1/messages', message)
  }
}

export default ApiClient