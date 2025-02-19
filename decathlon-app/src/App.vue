<script setup>
import { computed, ref, onMounted } from 'vue';
import EVENTS from '@/scripts/Events'
import decathlonClient from '@/scripts/decathlonClient';

let finishedEventsCount = ref(0)
let totalScore = ref(0)

const remainingEvents = computed(() => {
  if (events.value == null) return
  const savedEventTypes = new Set(events.value.map(item => item.eventType));
  return Object.entries(EVENTS)
    .filter(([key]) => !savedEventTypes.has(key))
    .reduce((acc, [key, value]) => {
      acc[key] = value;
      return acc;
    }, {});
});

const events = ref(null);
const loading = ref(true);
const error = ref(null);

const eventScoreInput = ref(null)
const selectedItem = ref(null);
const selectedKey = ref(null);


const openInput = (item, key, currentScore = null) => {
  selectedItem.value = item;
  selectedKey.value = key;
  eventScoreInput.value = currentScore
};

const closeInput = () => {
  eventScoreInput.value = null
  selectedItem.value = null;
  selectedKey.value = null;
};

const saveOrUpdateEvent = async () => {
  try {
    await decathlonClient.postEvent(selectedKey.value, eventScoreInput.value)


  } catch (err) {
    error.value = err.message;

    setTimeout(() => {
      error.value = null;
    }, 3000);
  }

  await getEvents()
  closeInput()
}

const deleteEvent = async (eventName) => {
  try {
    await decathlonClient.deleteEvent(eventName)
  } catch (err) {
    error.value = err.message;

    setTimeout(() => {
      error.value = null;
    }, 3000);
  }

  await getEvents()
}

const getEvents = async () => {
  try {
    events.value = await decathlonClient.getEvents()
    finishedEventsCount.value = events.value.length
    totalScore.value = events.value.reduce((val, item) => val + item.points, 0)
  } catch (err) {
    error.value = err.message;

    setTimeout(() => {
      error.value = null;
    }, 3000);
  } finally {
    loading.value = false;
  }
};

onMounted(getEvents);
</script>

<template>
  <transition name="fade">
    <div v-if="error" class="flash-message">{{ error }}</div>
  </transition>

  <h1> Welcome To Decathlon APP </h1>

  <div v-if="loading">Retrieving data...</div>
  <div v-else>
    <h2>Events 10 / {{ finishedEventsCount }} finished </h2>
    <h2>Total points : {{ totalScore }}</h2>


    <table>
      <thead>
        <tr>
          <th>EventType</th>
          <th>Points</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in events" :key="item.eventType">
          <td>{{ item.eventType }}</td>
          <td> {{ item.points }}</td>
          <td>
            <button @click="openInput(EVENTS[item.eventType], item.eventType, item.points)">Edit</button>
            <button @click="deleteEvent(item.eventType)">Delete</button>
          </td>
        </tr>
        <tr v-for="(item, key) in remainingEvents" :key="item.eventType">
          <td>{{ item.description }}</td>
          <td> </td>
          <td>
            <button @click="openInput(item, key)">Add Points</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <div v-if="selectedItem" class="modal">
    <div class="modal-content">
      <h3>Enter Score for {{ selectedItem.description }}</h3>
      <input type="number" v-model="eventScoreInput" :min="0" :max="selectedItem.maxScore" @keydown.enter="closeInput" />
      <button class="modal_save_btn" @click="saveOrUpdateEvent">Save</button>

      <h2 class="modal_close_btn" @click="closeInput">X</h2>
    </div>
  </div>
</template>

<style scoped>
table tr th:first-child {
  width: 24rem;
}

td,
th {
  border-bottom: 1px solid black;
}

thead {
  font-size: 1.25rem;
}

.box:hover {
  background: #999;
}

.box {
  background: #bdbdbd;
  margin-bottom: 10px;
  cursor: pointer;
  max-width: 16rem;
  padding: 0.5rem 1rem;
  margin: 1rem;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  position: relative;
  background: white;
  padding: 3rem;
  border-radius: 8px;
  text-align: center;
}

.modal_save_btn {
  height: 2rem;
  width: 4rem;
  margin: 1rem;
  background: rgb(155, 238, 155);
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
}

.modal_save_btn:hover {
  background: rgb(131, 221, 131);
}

.modal_close_btn {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  top: 0;
  right: 0;
  height: 2rem;
  width: 2rem;
  margin: 1rem;
  padding: 1rem;
  border-radius: 50%;
  background: rgb(187, 187, 187);
}

.modal_close_btn:hover {
  background: rgb(163, 162, 162);
}


input {
  padding: 5px;
  width: 80px;
  text-align: center;
}

.flash-message {
  background: red;
  color: white;
  padding: 10px;
  border-radius: 5px;
}

/* Vue Transition */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

@media (min-width: 1024px) {}
</style>
