<script setup lang="ts">
import { EventInfo } from '@/scripts/resources/interfaces';
import { ref, watch } from 'vue'

const emits = defineEmits(['update:score', 'save', 'close'])
const props = defineProps<{
  item: EventInfo,
  score?: number | null
}>()
const localScore = ref(props.score)


// Sync local score with parent value
watch(() => props.score, (newVal) => localScore.value = newVal)
// Update eventScoreInput in parentElement
watch(localScore, (val) => emits('update:score', val))
</script>

<template>
  <div class="modal">
    <div class="modal-content">
      <h3>Enter Score for {{ item.description }}</h3>
      <input type="number" v-model="localScore" :min="0" :max="item.maxScore" @keydown.enter="$emit('save')" />
      <button class="modal-save-btn" @click="$emit('save')">Save</button>
      <button class="modal-close-btn" @click="$emit('close')">X</button>
    </div>
  </div>
</template>


<style scoped>
input {
  padding: 5px;
  width: 80px;
  text-align: center;
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

.modal-save-btn {
  height: 2rem;
  width: 4rem;
  margin: 1rem;
  background:  rgb(95, 201, 95);
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
}

.modal-save-btn:hover {
  background:  rgb(113, 248, 113);
}

.modal-close-btn {
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

.modal-close-btn:hover {
  background: rgb(163, 162, 162);
}
</style>