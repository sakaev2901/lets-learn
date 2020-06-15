<template>
    <v-container>
        <v-card v-for="chat in chats" link class="chat-entity" :href="$store.getters.getUsername == 'dmr' ? '/chat/' +  chat.firstUser.username : '/chat/' +  chat.secondUser.username">
            <v-card-actions>
                <v-layout row>
                    <v-flex class="xs3">
                        <v-list-item>
                            <v-list-item-avatar>
                                <img :src=" $store.getters.getUsername == 'dmr' ? 'http://localhost:8080/api/profile/image/' +  chat.firstUser.imageName : 'http://localhost:8080/api/profile/image/' +  chat.secondUser.imageName">
                            </v-list-item-avatar>
                            <v-list-item-content>
                                <v-list-item-title>{{$store.getters.getUsername == 'dmr' ? chat.firstUser.name  : chat.secondUser.name}}</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                    </v-flex>
                    <v-divider vertical></v-divider>
                    <v-flex align-self-center>
                        <v-list-item>
                            <v-list-item-content class="message-preview">
                                Cообщение...
                            </v-list-item-content>
                        </v-list-item>
                    </v-flex>
                </v-layout>
            </v-card-actions>
        </v-card>


    </v-container>
</template>

<script>
    import {AXIOS} from "../main";

    export default {
        name: "Chat",
        data: () => ({
            userData: null,
            chats: [{main: {username: ''}}]
        }),
        methods: {
            loadData() {
                let self = this
                AXIOS.get("/userChats/" + this.$store.getters.getUsername)
                    .then(response => {
                        self.chats = response.data
                        console.log(self.chats)
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
                this.chats.forEach(el => {
                    if (el.firstUser.username == this.$store.getters.getUsername) {
                        el.main = el.secondUser
                    } else {
                        el.main = el.firstUser
                    }
                })
                AXIOS.get("/friends/" + this.$store.getters.getUsername)
                    .then(function (response) {
                        self.userData = response.data
                        console.log("in loadData")
                        console.log(response.data)
                        console.log(self.userData.pop())
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
            }
        },
        mounted() {
            this.loadData()
        }
    }
</script>

<style scoped>
    .message-preview {
        color: grey;
    }

    .chat-entity {
        margin: 20px 0;
    }
</style>
