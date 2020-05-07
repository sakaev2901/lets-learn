<template>
    <v-container>
        <v-card>
            <v-card-title>
                Дамир Нафиков
            </v-card-title>
            <v-card-text>
                <v-list>
                    <v-list-item v-for="message in messages" class="message-entity">
                        <v-list-item-avatar>
                            {{message.sender.name}}
                        </v-list-item-avatar>
                        <v-list-item-content>
                            {{message.text}}
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item>
                        <v-list-item-content>
                            <v-text-field outlined label="Сообщение..." v-model="text">

                            </v-text-field>
                            <v-btn @click="send()">
                                Отправить
                            </v-btn>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
    import SockJs from 'sockjs-client'
    import Stomp from 'webstomp-client'
    import * as router from "vue-router";
    import {AXIOS} from "../main";


    export default {
        name: "UserChat",
        data: () => ({
            text: '',
            messages: [],
            companion: "",
            username: "",
            room: ""

        }),
        methods: {
            send() {
                console.log("Send message:" + this.text);
                if (this.stompClient && this.stompClient.connected) {
                    const msg = {
                        text: this.text,
                        senderUsername: this.username,
                        receiverUsername: this.companion
                    };
                    console.log(JSON.stringify(msg));
                    this.stompClient.send("/api/message/"+ this.room, JSON.stringify(msg), {});
                }
            },
            connect() {
                this.socket = new SockJs('http://localhost:8080/connect')
                this.stompClient = Stomp.over(this.socket)
                this.stompClient.connect(
                    {},
                    frame => {
                        console.log(frame)
                            this.stompClient.subscribe("/chat/" + this.room, tick => {
                                console.log(JSON.parse(tick.body))
                                console.log(JSON.parse(tick.body).content)
                                this.messages.push(JSON.parse(tick.body))
                            })
                    },
                    error => {
                        console.log(error)
                    }
                )
            },
            generateRoomName() {
                if (this.username > this.companion) {
                    this.room = this.username + "-" + this.companion
                } else {
                    this.room = this.companion + "-" + this.username
                }
            },
            loadData() {
                let self = this
                AXIOS.get("/messages/" + this.room)
                    .then(function (response) {
                        console.log(response.data)
                        response.data.forEach(message => self.messages.push(message))
                        console.log(self.messages)

                    })
                    .catch(function (err) {
                        console.log(err)
                    })
            }
        },

        mounted() {
            // this.connect()
            console.log(44)
            this.companion = this.$router.history.current.params.username
            this.username = this.$store.getters.getUsername
            console.log(this.username + this.companion )
            this.generateRoomName()
            this.loadData()
            this.connect()
        }
    }
</script>

<style scoped>
    .message-entity {
        border: 1px grey solid;
        border-radius: 10px;
        margin: 10px 0;
    }
</style>
