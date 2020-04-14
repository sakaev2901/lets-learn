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
<!--        <v-img-->
<!--          alt="Vuetify Logo"-->
<!--          class="shrink mr-2"-->
<!--          contain-->
<!--          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"-->
<!--          transition="scale-transition"-->
<!--          width="40"-->
<!--        />-->

<!--        <v-img-->
<!--          alt="Vuetify Name"-->
<!--          class="shrink mt-1 hidden-sm-and-down"-->
<!--          contain-->
<!--          min-width="100"-->
<!--          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"-->
<!--          width="100"-->
<!--        />-->

      </div>

      <v-spacer></v-spacer>
        <span>Let's Learn</span>
<!--      <v-btn-->
<!--        href="https://github.com/vuetifyjs/vuetify/releases/latest"-->
<!--        target="_blank"-->
<!--        text-->
<!--      >-->
<!--        <span class="mr-2">Latest Release</span>-->
<!--        <v-icon>mdi-open-in-new</v-icon>-->
<!--      </v-btn>-->
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
                        <v-list-item-title>Application</v-list-item-title>
                        <v-list-item-subtitle>Subtext</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>

                <v-divider>
                </v-divider>

                <div v-for="item in items" :key="item.title">
                    <v-list-item :href="item.link">
                        <v-list-item-icon>
                            <v-icon>{{ item.icon }}</v-icon>
                        </v-list-item-icon>

                        <v-list-item-content>
                            <v-list-item-title>{{ item.title }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </div>

            </v-list>
        </v-navigation-drawer>
      <router-view></router-view>
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
          { title: 'Лента', icon: 'mdi-view-dashboard', link: "/feed" },
          { title: 'Профиль', icon: 'mdi-account', link: "/profile" },
          { title: 'Сообщения', icon: 'mdi-chat', link: "/chat" },
      ],
      drawer: true
  }),
};
</script>
