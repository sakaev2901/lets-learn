import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUp from "../views/SignUp";
import SignIn from "../views/SignIn";
import Profile from "../views/Profile";
import Confirmation from "../views/Confirmation";
import Test from "../views/Test";
import Chat from "../views/Chats";
import UserChat from "../views/UserChat";
import Feed from "../views/Feed";

Vue.use(VueRouter)

const routes = [
    {
        path: '/signUp',
        name: 'SignUp',
        component: SignUp
    },
    {
        path: '/chat/:username',
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
        path: '/profile/:username',
        name: 'Profile',
        component: Profile
    },
    {
        path: '/confirmation',
        name: "Confirmation",
        component: Confirmation
    },
    {
        path: '/feed',
        name: "feed",
        component: Feed
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
