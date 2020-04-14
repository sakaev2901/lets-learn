<template>
    <v-container>
        <v-card>
            <v-card-title>
                Дамир Нафиков
            </v-card-title>
            <v-card-text>
                <v-list>
                    <v-list-item class="message-entity">
                        <v-list-item-avatar>
                            <v-img src="https://randomuser.me/api/portraits/men/81.jpg"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            Привет
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item class="message-entity">
                        <v-list-item-avatar>
                            <v-img src="https://randomuser.me/api/portraits/men/82.jpg"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            Привет
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item class="message-entity">
                        <v-list-item-avatar>
                            <v-img src="https://randomuser.me/api/portraits/men/81.jpg"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            Привет
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item>
                        <v-list-item-content>
                            <v-text-field outlined label="Сообщение...">

                            </v-text-field>
                            <v-btn>
                                Отправить
                            </v-btn>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: "UserChat"
    }
</script>

<style scoped>
    .message-entity {
        border: 1px grey solid;
        border-radius: 10px;
        margin: 10px 0;
    }
</style>