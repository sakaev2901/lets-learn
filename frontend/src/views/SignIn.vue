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
                    <v-btn>
                        Войти
                    </v-btn>
                </v-form>

            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: "SignIn"
    }
</script>

<style scoped>

</style>