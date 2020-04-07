import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUp from "../views/SignUp";
import SignIn from "../views/SignIn";
import Profile from "../views/Profile";
import Confirmation from "../views/Confirmation";

Vue.use(VueRouter)

const routes = [
    {
        path: '/signUp',
        name: 'SignUp',
        component: SignUp
    },
    {
        path: '/signIn',
        name: 'SignIn',
        component: SignIn
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile
    },
    {
        path: '/confirmation',
        name: "Confirmation",
        component: Confirmation
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router