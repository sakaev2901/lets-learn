<template>
    <div>

        <v-card v-for="article in articles.slice().reverse()" :key="article.id" class="article">
            <v-img lazy-src="../assets/img/empty.jpg" contain class="grey align-end article-img"
                   :src="'http://localhost:8080/api/image/' + article.imageName">
            </v-img>
            <v-card-title>
                {{article.title}}
            </v-card-title>
            <v-card-actions>
                <v-list-item>
                    <v-list-item-avatar>
                        <img :src="'http://localhost:8080/api/profile/image/' + article.user.imageName">
                    </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title>{{article.user.name}} {{article.user.surname}}</v-list-item-title>
                    </v-list-item-content>
                    <div class="likes">
                        <v-btn v-if="article.isLiked" v-bind:class="{like: active}" icon text @click="doDislike">
                            <v-icon color="red" :id="article.id" link>mdi-heart</v-icon>
                        </v-btn>
                        <v-btn v-else icon text @click="doLike">
                            <v-icon :id="article.id" link>mdi-heart</v-icon>
                        </v-btn>
                        <span>{{article.likes.length}}</span>
                    </div>

                    <v-btn color="grey" dark>
                        Открыть
                    </v-btn>
                </v-list-item>
            </v-card-actions>
            <v-list>
                <v-list-item v-if="article.firstComment">
                    <v-list-item-avatar>
                        <img :src="'http://localhost:8080/api/profile/image/' + article.firstComment.owner.imageName">
                    </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title>{{article.firstComment.owner.name}} {{article.firstComment.owner.surname}}</v-list-item-title>
                        <v-list-item-subtitle>
                            {{article.firstComment.text}}
                        </v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <a @click="showComments = !showComments" v-if="!showComments">Открыть комментарии...</a>
                <v-list-item v-for="comment in article.comments" v-if="showComments">
                    <v-list-item-avatar>
                        <img :src="'http://localhost:8080/api/profile/image/' + comment.owner.imageName">
                    </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title>{{comment.owner.name}} {{comment.owner.surname}}</v-list-item-title>
                        <v-list-item-subtitle>
                            {{comment.text}}
                        </v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-content>
                        <v-text-field :id="article.id.toString()" @keyup.enter="doComment" outlined label="Коммент"/>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-card>
    </div>
</template>

<script>
    import {AXIOS} from "../main";

    export default {
        name: "Feed",
        data() {

            return {
                showComments: false,
                active: false,
                title: '',
                text: '',
                image: null,
                successUpload: false,
                url: '',
                articles: [{
                    firstComment: null,
                    likes: [],
                    comments: []
                }
                ],
                user: {},
                friends: [],
                dialog: false,
                comment: ''
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
                AXIOS.post("/post", formData, {headers: {'Authorization': 'Bearer ' + this.$store.getters.getToken}})
                    .then(
                        function (response) {
                            self.articles.push({title: self.title, text: self.text, imageName: response.data.imageName})
                        }
                    )
            },

            loadPage() {
                let self = this;
                this.currentUser = {
                    name: this.$store.getters.getName,
                    surname: this.$store.getters.getSurname,
                    username: this.$store.getters.getUsername
                }
                console.log('Current User')
                console.log(this.currentUser)
                AXIOS.get("/posts/" + this.username, {headers: {'Authorization': 'Bearer ' + this.$store.getters.getToken}}).then(
                    function (response) {
                        self.articles = response.data
                        console.log(self.articles)
                        self.articles.forEach(article => {
                            article.likes.forEach(c => {
                                if (c.username == self.$store.getters.getUsername) {
                                    article.isLiked = true
                                }
                            })
                            if (article.comments.length > 1) {
                                article.firstComment = article.comments[0]
                                article.comments.splice(0, 1)
                            }
                        })
                    }
                )
            },
            loadUserPosts() {
                let self = this
                console.log("sdfdsfsd")
                console.log(this.friends)
                this.friends.forEach(el => {
                    // console.log("sdfdsfdsfsd")
                    // AXIOS.get("/posts/" + el.username, {headers: {'Authorization': 'Bearer ' + this.$store.getters.getToken}}).then(
                    //     function (response) {
                    //         self.articles = response.data
                    //         console.log("USERUESURSER")
                    //         console.log(self.articles)
                    //         self.articles.forEach(article => {
                    //             article.likes.forEach(c => {
                    //                 if (c.username == self.$store.getters.getUsername) {
                    //                     article.isLiked = true
                    //                 }
                    //             })
                    //             if (article.comments.length > 1) {
                    //                 article.firstComment = article.comments[0]
                    //                 article.comments.splice(0, 1)
                    //             }
                    //         })
                    //     }
                    // )
                    console.log(45)
                })
            },
            openDialog() {
                console.log(45)
                this.dialog = true
            },
            doFriend() {
                AXIOS.post("/addFriend", {username: this.user.username}, {headers: {'Authorization': 'Bearer ' + this.$store.getters.getToken}})
                    .then(
                        response => console.log(response)
                    )
                    .catch(
                        err => console.log(err)
                    )
            },

            doLike: function (event) {
                let self = this
                AXIOS.post("/like", {username: this.$store.getters.getUsername, postId: event.target.id})
                    .then(function (response) {
                        const idx = self.articles.findIndex(c => c.id == event.target.id)
                        console.log(idx)
                        self.articles[idx].isLiked = true
                        self.articles[idx].likes.push({username: self.$store.getters.getUsername})
                    })
            },

            doDislike: function (event) {
                let self = this
                AXIOS.post("/dislike", {username: this.$store.getters.getUsername, postId: event.target.id})
                    .then(function (response) {
                        const idx = self.articles.findIndex(c => c.id == event.target.id)
                        console.log(idx)
                        self.articles[idx].isLiked = false
                        const idxLike = self.articles[idx].likes.findIndex(c => c.username == self.$store.getters.getUsername)
                        console.log(idxLike)
                        console.log(self.articles[idx].likes[idxLike].username)
                        self.articles[idx].likes.splice(idxLike, 1)
                        console.log(self.articles[idx].likes[idxLike].username)
                    })
            },
            doComment: function (event) {
                let self = this
                const id = event.target.id
                const comment = event.target.value
                const idx = this.articles.findIndex(c => c.id == id)
                console.log(id)
                console.log(comment)
                if (!this.articles[idx].comments) {
                    this.articles[idx].comments = []
                }
                AXIOS.post('/comment', {text: comment, username: this.$store.getters.getUsername, postId: id})
                    .then(
                        response => {
                            console.log('first comment')
                            console.log(self.articles[idx].firstComment)
                            if (!self.articles[idx].firstComment) {
                                self.articles[idx].firstComment = {
                                    text: comment,
                                    owner: {
                                        username: self.$store.getters.getUsername,
                                        name: self.$store.getters.getName,
                                        surname: self.$store.getters.getSurname,
                                        imageName: self.$store.getters.getAvatar
                                    }
                                }
                            } else {
                                self.articles[idx].comments.push({
                                    text: comment,
                                    owner: {
                                        username: self.$store.getters.getUsername,
                                        name: self.$store.getters.getName,
                                        surname: self.$store.getters.getSurname,
                                        imageName: self.$store.getters.getAvatar
                                    }
                                })
                            }
                            event.target.value = ''
                        }
                    )
                    .catch(
                        err => console.log(err)
                    )

            }
        },
        beforeCreate() {
            let self = this
            this.username = this.$store.getters.getUsername;
            AXIOS.get("/user/" + this.username, {headers: {'Authorization': 'Bearer ' + this.$store.getters.getToken}})
                .then(response => {
                    console.log('load user');
                    self.user = response.data
                    console.log(self.user)

                })
                .catch(err => {
                    console.log(err)
                })
            AXIOS.get("/friends/" + this.username, {headers: {'Authorization': 'Bearer ' + this.$store.getters.getToken}})
                .then(response => {
                    response.data.forEach(friend => self.friends.push(friend))
                })
                .catch(err => {
                    console.log(err)
                })

            console.log('Token in profile: ' + this.$store.getters.getToken)
        },
        mounted() {
            this.loadPage()
            this.loadUserPosts()
        }

    }
</script>

<style scoped>
    .article {
        margin: 30px 0;
    }

    .article-img {

        height: 400px;
    }

    .likes {
        margin: 0 40px;
    }

    .article-form-card {
        margin-top: 20px;
        padding: 10px 20px;

    }


    html {
        width: 100vw;
    }

</style>
