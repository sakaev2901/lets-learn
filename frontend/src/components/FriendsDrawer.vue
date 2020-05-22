<template>

</template>

<script>
    import {AXIOS} from "../main";

    export default {
        name: "FriendsDrawer",
        data: () => ({
            friends: []
        }),
        methods: {
            loadData() {
                let self = this
                AXIOS.get("/friends/" + this.$store.getters.getUsername)
                    .then(
                        response => {
                            response.data.forEach(el => self.friends.push(el))
                        }
                    )
                    .catch(
                        err => console.log(err)
                    )
            }
        },
        mounted() {
            this.loadData()
        }
    }
</script>

<style scoped>

</style>
