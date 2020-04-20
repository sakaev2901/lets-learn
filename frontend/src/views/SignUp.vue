<template>
    <v-container>
        <v-card>
            <v-card-title>Регистрация</v-card-title>
            <v-card-text>
                <v-form>
                    <v-text-field
                            label="Name"
                            v-model="name"
                    >
                    </v-text-field>
                    <v-text-field
                            label="Surname"
                            v-model="surname"
                    >
                    </v-text-field>
                    <v-text-field
                            label="Username"
                            v-model="username"
                    >
                    </v-text-field>
                    <v-text-field
                            v-model="mail"
                            label="E-mail"
                    >
                    </v-text-field>
                    <v-text-field
                            v-model="password"
                            label="Password"
                            required
                    >
                    </v-text-field>
                    <v-btn @click="signUp">
                        Submit
                    </v-btn>
                </v-form>
            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
    import {AXIOS} from "../main";

    export default {
        name: "SignUp",

        data: () => ({
            mail: '',
            username: '',
            password: '',
            name: '',
            surname: ''
        }),

        methods: {
            signUp() {
                let self = this;
                AXIOS.post('/signUp', {
                    mail: this.mail,
                    username: this.username,
                    password: this.password,
                    name: this.name,
                    surname: this.surname
                })
                    .then(function (response) {
                        const status = JSON.parse(response.status)
                        if (status == '200') {
                            self.$router.push('/confirmation')
                        }
                    })
                console.log(5)
            }
        }
    }
</script>

<style scoped>

</style>
