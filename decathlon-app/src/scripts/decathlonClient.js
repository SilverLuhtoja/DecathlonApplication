const URL = 'http://localhost:8000/api'

const decathlonClient = {
  getEvents: async () => {
    try {
      const response = await fetch(URL)
      return await response.json()
    } catch {
      throw new Error('Something went wrong! Seems server have some troubles.')
    }
  },

  postEvent: async (eventName, points) => {
    const requestBody = JSON.stringify({ eventName, points })
    const response = await fetch(URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: requestBody,
    })

    if (!response.ok) {
      let body = await response.json()
      throw new Error(body.message)
    }
  },

  deleteEvent: async (eventName) => {
    const response = await fetch(URL + '/delete/' + eventName, {
      method: 'DELETE',
    })

    if (!response.ok) {
      let body = await response.json()
      throw new Error(body.message)
    }
  },
}

export default decathlonClient
