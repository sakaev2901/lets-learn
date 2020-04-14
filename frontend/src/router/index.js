import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUp from "../views/SignUp";
import SignIn from "../views/SignIn";
import Profile from "../views/Profile";
import Confirmation from "../views/Confirmation";
import Test from "../views/Test";
import Chat from "../views/Chat";
import UserChat from "../views/UserChat";

Vue.use(VueRouter)

const routes = [
    {
        path: '/signUp',
        name: 'SignUp',
        component: SignUp
    },
    {
        path: '/userChat',
        name: 'UserChat',
        component: UserChat
    },
    {
        path: '/chat',
        name: 'Chat',
        component: Chat
    },
    {
        path: '/test',
        name: 'Test',
        component: Test
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
