import { createRouter, createWebHistory } from 'vue-router';
import RegisterLogin from '../components/RegisterLogin.vue';
import Home from '../components/Home.vue';
import CrearTarea from '../components/CrearTarea.vue';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: RegisterLogin,
    meta: { requiresAuth: false }
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: { requiresAuth: true }
  },
  {
    path: '/crearTarea',
    name: 'CrearTarea',
    component: CrearTarea,
    meta: { requiresAuth: true }
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
