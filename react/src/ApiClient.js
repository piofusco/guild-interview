import axios from "axios"

class ApiClient {
  static async postMessage(message) {
    return await axios.post('/api/v1/messages', message)
  }
}

export default ApiClient