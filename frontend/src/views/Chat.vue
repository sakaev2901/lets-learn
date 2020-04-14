<template>
    <v-container>
                <v-card link class="chat-entity" href="/userChat">
                    <v-card-actions>
                        <v-layout row>
                            <v-flex class="xs3">
                                <v-list-item>
                                    <v-list-item-avatar>
                                        <v-img src="https://randomuser.me/api/portraits/men/82.jpg"></v-img>
                                    </v-list-item-avatar>
                                    <v-list-item-content>
                                        <v-list-item-title>Дамир Нафиков</v-list-item-title>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-flex>
                            <v-divider vertical></v-divider>
                            <v-flex  align-self-center>
                                <v-list-item>
                                    <v-list-item-content class="message-preview">
                                        Голосовое сообщение...
                                    </v-list-item-content>
                                </v-list-item>
                            </v-flex>
                        </v-layout>
                    </v-card-actions>
                </v-card>

                <v-card link class="chat-entity">
                    <v-card-actions>
                        <v-layout row>
                            <v-flex class="xs3">
                                <v-list-item>
                                    <v-list-item-avatar>
                                        <v-img src="https://randomuser.me/api/portraits/men/82.jpg"></v-img>
                                    </v-list-item-avatar>
                                    <v-list-item-content>
                                        <v-list-item-title>Дамир Нафиков</v-list-item-title>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-flex>
                            <v-divider vertical></v-divider>
                            <v-flex  align-self-center>
                                <v-list-item>
                                    <v-list-item-content class="message-preview">
                                        Голосовое сообщение...
                                    </v-list-item-content>
                                </v-list-item>
                            </v-flex>
                        </v-layout>
                    </v-card-actions>
                </v-card>

    </v-container>

</template>

<script>
    export default {
        name: "Chat"
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