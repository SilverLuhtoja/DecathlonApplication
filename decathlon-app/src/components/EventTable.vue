<script setup lang="ts">
import { EVENTS, EventKey } from '@/scripts/resources/events'
import { computed } from 'vue';
import { EventInfo, EventItem } from '@/scripts/resources/interfaces';

const props = defineProps<{
    events: EventItem[]
}>()

const remainingEvents = computed(() => {
    if (!props.events) return {}
    const savedEventTypes = new Set(props.events.map(item => item.eventType))
    return Object.entries(EVENTS)
        .filter(([key]) => !savedEventTypes.has(key))
        .reduce((acc, [key, value]) => {
            acc[key] = value;
            return acc;
        }, {} as Record<string, EventInfo>)
})

</script>

<template>
    <table>
        <thead>
            <tr>
                <th>EventType</th>
                <th>Points</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Finished events -->
            <tr v-for="item in events" :key="item.eventType">
                <td>{{ item.eventType }}</td>
                <td>{{ item.points }}</td>
                <td>
                    <button class="edit-btn" @click="$emit('edit-event', EVENTS[item.eventType as EventKey], item.eventType, item.points)">Edit</button>
                    <button class="delete-btn" @click="$emit('delete-event', item.eventType)">Delete</button>
                </td>
            </tr>
            <!-- Remaining events -->
            <tr v-for="(item, key) in remainingEvents" :key="key">
                <td>{{ item.description }}</td>
                <td></td>
                <td>
                    <button class="add-btn" @click="$emit('edit-event', item, key)">Add Points</button>
                </td>
            </tr>
        </tbody>
    </table>
</template>

<style scoped>
table tr th:first-child {
    width: 24rem;
}

td,
th {
    border-bottom: 1px solid black;
    padding: 0.5rem 0;
}

thead {
    font-size: 1.25rem;
}

button {
    border: none;
    border-radius: 0.2rem;
    margin: 0.1rem;
    min-height: 1.5rem;
    padding: 0.2rem 0.5rem;
    font-weight: 600;
}

.add-btn {
    background-color: rgb(95, 201, 95);
}

.add-btn:hover {
    background-color: rgb(113, 248, 113);
}

.edit-btn {
    background-color: rgb(157, 159, 233);
}

.edit-btn:hover {
    background-color:  rgb(196, 198, 255);
}

.delete-btn {
    background-color: rgb(219, 141, 89);
}

.delete-btn:hover {
    background-color:  rgb(255, 171, 115);
}
</style>