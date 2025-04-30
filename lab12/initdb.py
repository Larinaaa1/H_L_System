import { check } from 'k6';
import http from 'k6/http';
import { randomString } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';
import { Writer, SCHEMA_TYPE_STRING, SchemaRegistry } from 'k6/x/kafka';

const topic = __ENV.KAFKA_TOPIC  'var08_lar';
const brokers = [__ENV.KAFKA_BROKER  'hl22.zil:9094'];

const writer = new Writer({
    brokers: brokers,
    topic: topic,
});

const schemaRegistry = new SchemaRegistry(); # сериализация строк

export const options = {
    scenarios: {
        writers: {
            executor: 'constant-vus',
            vus: __ENV.VUS_WRITE ? parseInt(__ENV.VUS_WRITE) : 1,
duration: '1m',
exec: 'writeScenario',
},
readers: {
    executor: 'constant-vus',
    vus: __ENV.VUS_READ ? parseInt(__ENV.VUS_READ) : 10,
duration: '1m',
exec: 'readScenario',
},
},
};

# Генерация Kafka-сообщения только для VIEWER
function generateViewerMessage() {
return JSON.stringify({
    entity: 'VIEWER',
    operation: 'POST',
    payload: {
        name: randomString(10),
        email: user${Math.floor(Math.random() * 10000)}@mail.com,
},
});
}

# Пишем в Kafka
export function writeScenario() {
const message = generateViewerMessage();

writer.produce({
    messages: [
        {
            key: schemaRegistry.serialize({
                data: randomString(4),
                schemaType: SCHEMA_TYPE_STRING,
            }),
            value: schemaRegistry.serialize({
                data: message,
                schemaType: SCHEMA_TYPE_STRING,
            }),
        },
    ],
});
}

# Делаем доп задание — запрос к stats
export function readScenario() {
const res = http.get('http://192.168.1.232:8081/stats/', {
    timeout: '360s',
});

check(res, { 'stats retrieved successfully': r => r.status === 200 });
}
#VUS_WRITE=5 VUS_READ=20 k6 run script.js