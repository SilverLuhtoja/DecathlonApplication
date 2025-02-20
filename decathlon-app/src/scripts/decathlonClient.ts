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

  postEvent: async (eventName: string | null, points: number | null) => {
    const requestBody = JSON.stringify({ eventName, points })
    const response = await fetch(URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: requestBody,
    })

    if (!response.ok) {
      const body = await response.json()
      throw new Error(body.message)
    }
  },

  deleteEvent: async (eventName: string) => {
    const response = await fetch(URL + '/delete/' + eventName, {
      method: 'DELETE',
    })

    if (!response.ok) {
      const body = await response.json()
      throw new Error(body.message)
    }
  },
}

export default decathlonClient
