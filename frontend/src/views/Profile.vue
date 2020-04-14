<template>
    <div>

            <v-container class="main">
                <v-card class="article-form-card">
                    <v-form>
                        <v-text-field v-model="title" outlined label="Заголовок">
                        </v-text-field>
                        <v-textarea v-model="text" outlined label="Описание">
                        </v-textarea>
                        <v-file-input v-model="image" label="Превью фото" outlined prepend-icon="mdi-camera" ref="image">
                        </v-file-input>
                        <v-card-actions>
                            <v-btn color="grey" dark @click="sendPost">
                                Запостить
                            </v-btn>
                        </v-card-actions>
                    </v-form>
                </v-card>

                <v-card v-if="successUpload" class="article">
                    <v-img lazy-src="../assets/img/empty.jpg" contain class="grey align-end article-img" :src="url">

                    </v-img>
                    <v-card-title>
                        {{title}}
                    </v-card-title>
                    <v-card-actions>
                        <v-list-item>
                            <v-list-item-avatar>
                                <v-img src="https://randomuser.me/api/portraits/men/81.jpg"></v-img>
                            </v-list-item-avatar>
                            <v-list-item-content>
                                <v-list-item-title>Eldar Sakaev</v-list-item-title>
                            </v-list-item-content>
                            <div class="likes">
                                <v-icon>mdi-heart</v-icon>
                                <span>165</span>
                            </div>

                            <v-btn color="grey" dark>
                                Открыть
                            </v-btn>
                        </v-list-item>
                    </v-card-actions>
                </v-card>
                <v-card class="article">
                    <v-img lazy-src="../assets/img/empty.jpg" contain class="grey align-end article-img" src="../assets/img/card.png">

                    </v-img>
                    <v-card-title>
                        Заголовок
                    </v-card-title>
                    <v-card-actions>
                        <v-list-item>
                            <v-list-item-avatar>
                                <v-img src="https://randomuser.me/api/portraits/men/81.jpg"></v-img>
                            </v-list-item-avatar>
                            <v-list-item-content>
                                <v-list-item-title>Eldar Sakaev</v-list-item-title>
                            </v-list-item-content>
                            <div class="likes">
                                <v-icon>mdi-heart</v-icon>
                                <span>165</span>
                            </div>

                            <v-btn color="grey" dark>
                                Открыть
                            </v-btn>
                        </v-list-item>
                    </v-card-actions>
                </v-card>
                <v-card class="article">
                    <v-img lazy-src="../assets/img/empty.jpg" contain class="grey align-end article-img" src="../assets/img/card.png">

                    </v-img>
                    <v-card-title>
                        Заголовок
                    </v-card-title>
                    <v-card-actions>
                        <v-list-item>
                            <v-list-item-avatar>
                                <v-img src="https://randomuser.me/api/portraits/men/81.jpg"></v-img>
                            </v-list-item-avatar>
                            <v-list-item-content>
                                <v-list-item-title>Eldar Sakaev</v-list-item-title>
                            </v-list-item-content>
                            <div class="likes">
                                <v-icon>mdi-heart</v-icon>
                                <span>165</span>
                            </div>

                            <v-btn color="grey" dark X>
                                Открыть
                            </v-btn>
                        </v-list-item>
                    </v-card-actions>
                </v-card>
            </v-container>

    </div>
</template>

<script>
    import {AXIOS} from "../main";

    export default {
        name: "Profile",
        data () {

            return{
                title: '',
                text: '',
                image: null,
                successUpload: false,
                url: ''
            }
        },
        methods: {
            sendPost() {
                self = this;
                let formData = new FormData()
                formData.append("image", this.image);
                formData.append("title", this.title);
                formData.append("text", this.text);
                console.log(this.image);
                console.log(formData.get("text"))
                AXIOS.post("/post", formData).then(
                    function () {
                        self.successUpload = true
                        self.url = URL.createObjectURL(self.image)
                    }
                )
            }

        }
    }
</script>

<style scoped>
    .article {
        margin: 30px 0;
    }

     .article-img{
        height: 400px;
    }

    .likes {
        margin: 0 40px;
    }

    .article-form-card {
        padding: 10px 20px;
    }
</style>