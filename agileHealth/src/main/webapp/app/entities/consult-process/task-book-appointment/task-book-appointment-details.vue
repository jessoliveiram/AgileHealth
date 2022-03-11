<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('agileHealthApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('agileHealthApp.taskBookAppointment.mode')">mode</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="mode"
                id="consult-mode"
                data-cy="mode"
                v-model="taskContext.consultProcess.consult.mode"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('agileHealthApp.taskBookAppointment.medicalSpecialty')">medicalSpecialty</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="medicalSpecialty"
                id="consult-medicalSpecialty"
                data-cy="medicalSpecialty"
                v-model="taskContext.consultProcess.consult.medicalSpecialty"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('agileHealthApp.taskBookAppointment.local')">local</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="local"
                id="consult-local"
                data-cy="local"
                v-model="taskContext.consultProcess.consult.local"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('agileHealthApp.taskBookAppointment.date')">date</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="date"
                id="consult-date"
                data-cy="date"
                v-model="taskContext.consultProcess.consult.date"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/ConsultProcess/task/TaskBookAppointment/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-book-appointment-details.component.ts"></script>
