import { createRouter, createWebHistory } from 'vue-router';
import RegisterLogin from '../components/RegisterLogin.vue';
import Home from '../components/Home.vue';
import CrearTarea from '../components/CrearTarea.vue';
import ListaTareas from '@/components/ListaTareas.vue';
import DetalleTarea from '../components/DetalleTarea.vue';
import EditarTarea from '@/components/EditarTarea.vue';
import Notificaciones from '@/components/Notificaciones.vue';


const routes = [
  {
    path: '/',
    name: 'Login',
    component: RegisterLogin,
    meta: { requiresAuth: false, hideNavBar: true }
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
  {
    path: '/listaTareas', 
    name: 'ListaTareas',
    component: ListaTareas,
    meta: { requiresAuth: true },
  },
  {
    path: '/DetalleTarea/:id',
    name: 'DetalleTarea',
    component: DetalleTarea,
    meta: { requiresAuth: true },
  },
  {
    path: '/EditarTarea/:id',
    name: 'EditarTarea',
    component: EditarTarea,
    meta: { requiresAuth: true },
  },
  {
    path: '/notificacion',
    name: 'Notificaciones',
    component: Notificaciones,
    meta: { requiresAuth: true },
  }
 

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
