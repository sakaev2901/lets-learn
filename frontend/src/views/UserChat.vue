<template>
    <v-container class="chat-container">
        <v-card class="chat-card">
            <v-card-title v-if="username !== chat.firstUser.username" class="chat-title">
                    {{chat.firstUser.username}}
            </v-card-title>
            <v-card-title v-else class="chat-title">
                {{chat.secondUser.name}} {{chat.secondUser.surname}}
            </v-card-title>
            <v-divider/>
            <v-card-text style="">
                <v-list class="chat-list">
                    <v-list-item v-for="message in messages" class="message-entity">
                        <v-list-item-avatar>
                            <img :src="'http://localhost:8080/api/profile/image/' + message.sender.imageName">
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title>
                                {{message.sender.name}}
                            </v-list-item-title>
                            {{message.text}}

                        </v-list-item-content>
                    </v-list-item>
                </v-list>
                <v-divider/>
                <v-list-item>
                    <v-list-item-content>
                        <v-text-field @keyup.enter="send()" outlined label="Сообщение..." v-model="text">

                        </v-text-field>
                        <v-btn @keyup.enter="send()">
                            Отправить
                        </v-btn>
                    </v-list-item-content>
                </v-list-item>
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
            room: "",
            chat: {}

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
                    this.stompClient.send("/api/message/" + this.room, JSON.stringify(msg), {});
                    this.text = ''
                    setTimeout(this.scrollToBottom, 100)
                }
            },
            scrollToBottom() {
                this.$nextTick(() => {
                    var container = this.$el.querySelector(".chat-list");
                    container.scrollTop = container.scrollHeight + 10;
                    // var el = document.querySelectorAll('.message-entity')
                    // var last = el[el.length - 1]
                    // last.scrollIntoView({block: "center", behavior: "smooth"})
                });
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
                AXIOS.get("/user/chat/" + this.room)
                    .then(response => {
                        self.chat = response.data
                        console.log(self.chat)
                    })
                    .catch(err => console.log(err))
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
            console.log(this.username + this.companion)
            this.generateRoomName()
            this.loadData()
            this.scrollToBottom()
            this.connect()
            setTimeout(this.scrollToBottom, 300)
            // this.scrollToBottom()
        }
    }
</script>

<style scoped>
    .message-entity {
        border: 1px grey solid;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        border-bottom-right-radius: 10px;
        margin: 10px 0;
        width: 50%;
    }

    .chat-card {
        height: 90vh;
        /*overflow-y: scroll;*/
    }

    .chat-list {
        overflow-y: auto;
        box-sizing: border-box;
        height: 60vh
    }

    /*.chat-list::-webkit-scrollbar {*/
    /*    display: none;*/
    /*}*/
    .chat-title {

    }
</style>
