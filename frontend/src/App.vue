<template>
    <v-app>
        <v-app-bar
                app
                color="blue"
                dark
                flat
                dense
                hide-on-scroll
        >
            <div class="d-flex align-center">
            </div>

            <v-spacer></v-spacer>
            <span>Let's Learn</span>
        </v-app-bar>

        <v-content>
            <v-navigation-drawer
                    v-model="drawer"
                    color="blue"
                    :mini-variant="true"
                    dark
                    expand-on-hover
                    permanent
                    floating
                    fixed
            >
                <v-list nav>
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


                    <v-list-item v-for="item in items" :key="item.title" :href="item.link">
                        <v-list-item-icon>
                            <v-icon>{{ item.icon }}</v-icon>
                        </v-list-item-icon>

                        <v-list-item-content>
                            <v-list-item-title>{{ item.title }}</v-list-item-title>
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
            items: [
                {title: 'Лента', icon: 'mdi-view-dashboard', link: "/feed"},
                {title: 'Профиль', icon: 'mdi-account', link: "/profile"},
                {title: 'Сообщения', icon: 'mdi-chat', link: "/chat"}
            ],
            drawer: true
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
</style>
