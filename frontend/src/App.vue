<template>
    <v-app>
        <v-content>
            <v-navigation-drawer
                    v-model="drawer"
                    color="blue"
                    :mini-variant="true"
                    dark
                    expand-on-hover
                    permanent
                    fixed
            >
                <v-list nav>
                    <v-list-item>
                        <v-list-item-content>
                            <span class="logo">L<span class="second-l">L</span></span>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item two-line class="px-0" link="" :href="'/profile/' + this.$store.getters.getUsername">
                        <v-list-item-avatar>
                            <img :src="'http://localhost:8080/api/profile/image/' + this.$store.getters.getAvatar">
<!--                            <img src="https://randomuser.me/api/portraits/men/81.jpg">-->
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title>{{user.name}}</v-list-item-title>
                            <v-list-item-subtitle>{{user.surname}}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>

                    <v-divider>
                    </v-divider>

                    <v-list-item link="" href="/feed">
                        <v-list-item-icon>
                            <v-icon>mdi-view-dashboard</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>Лента</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item link @click.right.prevent="friendsDrawer = !friendsDrawer">
                        <v-list-item-icon>
                            <v-badge color="red" content="6" overlap>
                                <v-icon>mdi-account-multiple</v-icon>
                            </v-badge>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>Друзья</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item link="" href="/chat" @click.right.prevent="showChatBriefly()">
                        <v-list-item-icon>
                            <v-badge color="red" :content="this.messages.length" overlap v-if="this.messages.length > 0">
                                <v-icon>mdi-chat</v-icon>
                            </v-badge>
                            <span v-else>
                                <v-icon>mdi-chat</v-icon>
                            </span>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>Сообщения</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item link="" href="/settings">
                        <v-list-item-icon>
                            <v-icon>mdi-cog</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>Настройки</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>


                    <v-list-item link="" @click="logout()">
                        <v-list-item-icon>
                            <v-icon>mdi-logout</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>Выйти</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>


                </v-list>
            </v-navigation-drawer>
            <v-navigation-drawer
                    v-model="friendsDrawer"
                    fixed
                    temporary
                    width="40vw"
                    color="red"
                    dark
            >
                <v-list nav>
                    <v-list-item v-for="friend of friends" link :href="'/profile/' + friend.username">
                        <v-list-item-avatar>
                            <img :src="'http://localhost:8080/api/profile/image/' + friend.imageName">
                        </v-list-item-avatar>
                        <v-list-item-content>
                            {{friend.name}}
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
            <v-container class="main">
                <router-view></router-view>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import HelloWorld from './components/HelloWorld';
    import {AXIOS} from "./main";
    import SockJs from 'sockjs-client'
    import Stomp from 'webstomp-client'

    export default {
        name: 'App',

        components: {
            HelloWorld,
        },

        data: () => ({
            drawer: false,
            friends: [],
            friendsDrawer: false,
            user: {},
            messages: []
        }),
        methods: {
            logout() {
                this.$store.dispatch('logout')
                this.$router.push("/signIn")
            },
            loadData() {
                let self = this
                AXIOS.get("/friends/" + this.$store.getters.getUsername)
                    .then(
                        response => {
                            console.log(response.data)
                            response.data.forEach(el => self.friends.push(el))
                        }
                    )
                    .catch(
                        err => console.log(err)
                    )
                AXIOS.get("/profile/" + this.$store.getters.getUsername)
                    .then(
                        response => {
                            self.user = response.data
                        }
                    )
                    .catch(
                        err => console.log(err)
                    )
            },
            showChatBriefly() {
                console.log("Heeey")
            },
            connect() {

                this.socket = new SockJs('http://localhost:8080/connect')
                this.stompClient = Stomp.over(this.socket)
                this.stompClient.connect(
                    {},
                    frame => {
                        console.log(frame)
                        this.stompClient.subscribe("/chat/", tick => {
                            console.log(JSON.parse(tick.body))
                            console.log(JSON.parse(tick.body).content)
                            this.messages.push(JSON.parse(tick.body))
                        })
                    },
                    error => {
                        console.log(error)
                    }
                )
            }
        },
        mounted() {
            this.loadData()
            this.connect()
        }
    };
</script>

<style scoped>
    .main {
        width: 80vw;
    }

    .logo {
        font-size: 20px;
        font-weight: bold;
        letter-spacing: 3px;
    }

    .second-l {
        transform: rotate(20deg);
    }
</style>
