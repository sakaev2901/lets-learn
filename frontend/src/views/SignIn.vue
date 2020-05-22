<template>
    <v-container>
        <v-card>
            <v-card-title>
                Вход
            </v-card-title>
            <v-card-text>
                <v-form>
                    <v-text-field label="Username" v-model="username">
                    </v-text-field>
                    <v-text-field label="Password" type="password" v-model="password">
                    </v-text-field>
                    <v-btn @click="signIn">
                        Войти
                    </v-btn>
                </v-form>
                <form method="get">
                    <input type="hidden" value="1234" name="_csrf">
                    <input type="submit">
                </form>
            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
    import {AXIOS} from "../main";

    export default {
        name: "SignIn",
        data: () => ({
            username: '',
            password: ''
        }),
        methods: {
            signIn() {
                let self = this;
                AXIOS.post("/signIn", {
                    username: self.username,
                    password: self.password
                })
                    .then(function (response) {

                        console.log(response.data.name)
                        self.$store.state.name = response.data.name
                        self.$store.state.surname = response.data.surname
                        self.$store.state.username = response.data.username
                        self.$store.state.token = response.data.token
                        self.$store.state.avatar = response.data.avatar
                        self.$store.dispatch('login', {
                                token: response.data.token,
                                username: response.data.username,
                                role: response.data.role,
                                name: response.data.name,
                                surname: response.data.surname,
                                avatar: response.data.avatar
                            }).then(
                                function () {
                                    self.$router.push("/profile/" + response.data.username)
                                }
                            )
                        }
                    ).catch(
                    function (response) {
                        alert(response)
                    }
                )
            }
        }
    }
</script>

<style scoped>

</style>
