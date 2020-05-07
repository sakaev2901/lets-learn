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
                    <v-list-item two-line class="px-0">
                        <v-list-item-avatar>
                            <img src="https://randomuser.me/api/portraits/men/81.jpg">
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title>Eldar</v-list-item-title>
                            <v-list-item-subtitle>Sakaev</v-list-item-subtitle>
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
                    <v-list-item link="" :href="'/profile/' + this.$store.getters.getUsername">
                        <v-list-item-icon>
                            <v-icon>mdi-account</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>Профиль</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item link="" href="/friends">
                        <v-list-item-icon>
                            <v-badge color="red" content="6" overlap>
                                <v-icon>mdi-account-multiple</v-icon>
                            </v-badge>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>Друзья</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item link="" href="/chat">
                        <v-list-item-icon>
                            <v-badge color="red" content="5" overlap>
                                <v-icon>mdi-chat</v-icon>
                            </v-badge>
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
            <v-container class="main">
                <router-view></router-view>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import HelloWorld from './components/HelloWorld';

    export default {
        name: 'App',

        components: {
            HelloWorld,
        },

        data: () => ({
            drawer: false
        }),
        methods: {
            logout() {
                this.$store.dispatch('logout')
                this.$router.push("/signIn")
            }
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
    .second-l  {
        transform: rotate(20deg);
    }
</style>
