import { EventInfo } from "./interfaces";

type EventKey =
  | 'HUNDRED_METERS'
  | 'LONG_JUMP'
  | 'SHOT_PUT'
  | 'HIGH_JUMP'
  | 'FOUR_HUNDRED_METERS'
  | 'HUNDRED_TEN_METERS_HURDLES'
  | 'DISCUS_THROW'
  | 'POLE_VAULT'
  | 'JAVELIN_THROW'
  | 'FIFTEEN_HUNDRED_METERS';

const EVENTS: Record<EventKey, EventInfo> = {
  HUNDRED_METERS: { description: '100 meters', maxScore: 1202 },
  LONG_JUMP: { description: 'Long jump', maxScore: 1312 },
  SHOT_PUT: { description: 'Shot put', maxScore: 1291 },
  HIGH_JUMP: { description: 'High jump', maxScore: 1250 },
  FOUR_HUNDRED_METERS: { description: '400 meters', maxScore: 1188 },
  HUNDRED_TEN_METERS_HURDLES: { description: '110 meters hurdles', maxScore: 1185 },
  DISCUS_THROW: { description: 'Discus throw', maxScore: 1370 },
  POLE_VAULT: { description: 'Pole vault', maxScore: 1257 },
  JAVELIN_THROW: { description: 'Javelin throw', maxScore: 1360 },
  FIFTEEN_HUNDRED_METERS: { description: '1500 meters', maxScore: 1296 },
};

export { EVENTS, EventKey };