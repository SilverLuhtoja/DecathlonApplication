<script setup lang="ts">
import { ref, onMounted } from 'vue';
import FlashMessage from '@/components/FlashMessage.vue'
import EventTable from '@/components/EventTable.vue'
import ScoreModal from '@/components/ScoreModal.vue'
import { EventInfo, EventItem } from '@/scripts/resources/interfaces';
import decathlonClient from '@/scripts/decathlonClient';

const selectedItem = ref<EventInfo | null>(null)
const selectedKey = ref<string | null>(null)
const eventScoreInput = ref<number>(0)
const error = ref<string | null>(null)
const events = ref<EventItem[]>([])
const loading = ref<boolean>(true)
const finishedEventsCount = ref<number>(0)
const totalScore = ref<number>(0)

function openInput(item: EventInfo, key: string, currentScore: number): void {
  selectedItem.value = item
  selectedKey.value = key
  eventScoreInput.value = currentScore
}

function closeInput(): void {
  eventScoreInput.value = 0
  selectedItem.value = null
  selectedKey.value = null
}

const saveOrUpdateEvent = async (): Promise<void> => {
  await handleApiCall(() => decathlonClient.postEvent(selectedKey.value, eventScoreInput.value))
  await getEvents();
  closeInput();
}

const deleteEvent = async (eventName: string): Promise<void> => {
  await handleApiCall(() => decathlonClient.deleteEvent(eventName));
  await getEvents();
}

const getEvents = async (): Promise<void> => {
  const response = await handleApiCall(() => decathlonClient.getEvents());
  if (response) {
    events.value = response as EventItem[];
    finishedEventsCount.value = events.value.length;
    totalScore.value = events.value.reduce((sum, item) => sum + item.points, 0);
  }
  loading.value = false;
}

async function handleApiCall<T>(apiFunc: () => Promise<T>): Promise<T | null> {
  try {
    return await apiFunc();
  } catch (err: any) {
    error.value = err.message || 'An error occurred.';
    setTimeout(() => { error.value = null }, 3000);
    return null;
  }
}

onMounted(getEvents);
</script>

<template>
  <main>
    <transition name="fade">
      <FlashMessage v-if="error" :message="error" />
    </transition>
    
    <h1> Decathlon APP </h1>
      
    <div v-if="loading">Retrieving data...</div>
    <div v-else>
      <h2>Events 10 / {{ finishedEventsCount }} finished </h2>
      <h2>Total points : {{ totalScore }}</h2>
      
      <EventTable
        :events="events"
        @edit-event="openInput"
        @delete-event="deleteEvent"
      />
    </div>

    <ScoreModal
      v-if="selectedItem"
      :item="selectedItem"
      v-model:score="eventScoreInput"
      @save="saveOrUpdateEvent"
      @close="closeInput"
    />
  </main>
</template>

<style scoped>
main{
  background: rgb(255, 255, 255);
  padding: 2rem;
  border-radius: 1rem;
}

h1{
  text-align: center;
}
</style>
