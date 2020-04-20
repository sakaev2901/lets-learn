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
                    <v-text-field label="Password" v-model="password">
                    </v-text-field>
                    <v-btn @click="signIn">
                        Войти
                    </v-btn>
                </v-form>

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
                        console.log(response.data.token)
                            self.$store.dispatch('login', {
                                token: response.data.token,
                                username: response.data.username,
                                role: response.data.role
                            })
                            self.$router.push("/profile")
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
