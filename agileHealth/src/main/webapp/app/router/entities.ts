import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Consult = () => import('@/entities/consult/consult.vue');
// prettier-ignore
const ConsultDetails = () => import('@/entities/consult/consult-details.vue');
// prettier-ignore
const ConsultProcess_TaskBookAppointmentDetails = () => import('@/entities/consult-process/task-book-appointment/task-book-appointment-details.vue');
// prettier-ignore
const ConsultProcess_TaskBookAppointmentExecute = () => import('@/entities/consult-process/task-book-appointment/task-book-appointment-execute.vue');
// prettier-ignore
const ConsultProcessDetails = () => import('@/entities/consult-process/consult-process-details.vue');
// prettier-ignore
const ConsultProcessList = () => import('@/entities/consult-process/consult-process-list.vue');
// prettier-ignore
const Doctor = () => import('@/entities/doctor/doctor.vue');
// prettier-ignore
const DoctorUpdate = () => import('@/entities/doctor/doctor-update.vue');
// prettier-ignore
const DoctorDetails = () => import('@/entities/doctor/doctor-details.vue');
// prettier-ignore
const ConsultStartFormInit = () => import('@/entities/consult-process/consult-start-form-init.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/consult',
    name: 'Consult',
    component: Consult,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/consult/:consultId/view',
    name: 'ConsultView',
    component: ConsultDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ConsultProcess/task/TaskBookAppointment/:taskInstanceId/view',
    name: 'ConsultProcess_TaskBookAppointmentDetails',
    component: ConsultProcess_TaskBookAppointmentDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ConsultProcess/task/TaskBookAppointment/:taskInstanceId/execute',
    name: 'ConsultProcess_TaskBookAppointmentExecute',
    component: ConsultProcess_TaskBookAppointmentExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ConsultProcess/instance/:processInstanceId/view',
    name: 'ConsultProcessView',
    component: ConsultProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ConsultProcess/instances',
    name: 'ConsultProcessList',
    component: ConsultProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/doctor',
    name: 'Doctor',
    component: Doctor,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/doctor/new',
    name: 'DoctorCreate',
    component: DoctorUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/doctor/:doctorId/edit',
    name: 'DoctorEdit',
    component: DoctorUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/doctor/:doctorId/view',
    name: 'DoctorView',
    component: DoctorDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/ConsultProcess/init',
    name: 'ConsultStartFormInit',
    component: ConsultStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
