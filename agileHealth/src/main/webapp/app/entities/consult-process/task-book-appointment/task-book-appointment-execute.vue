<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('agileHealthApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('agileHealthApp.taskBookAppointment.mode')" for="task-book-appointment-mode"
                >Mode</label
              >
              <input
                type="text"
                class="form-control"
                name="mode"
                id="task-book-appointment-mode"
                readonly
                data-cy="mode"
                :class="{
                  valid: !$v.taskContext.consultProcess.consult.mode.$invalid,
                  invalid: $v.taskContext.consultProcess.consult.mode.$invalid,
                }"
                v-model="$v.taskContext.consultProcess.consult.mode.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('agileHealthApp.taskBookAppointment.medicalSpecialty')"
                for="task-book-appointment-medicalSpecialty"
                >Medical Specialty</label
              >
              <input
                type="text"
                class="form-control"
                name="medicalSpecialty"
                id="task-book-appointment-medicalSpecialty"
                data-cy="medicalSpecialty"
                :class="{
                  valid: !$v.taskContext.consultProcess.consult.medicalSpecialty.$invalid,
                  invalid: $v.taskContext.consultProcess.consult.medicalSpecialty.$invalid,
                }"
                v-model="$v.taskContext.consultProcess.consult.medicalSpecialty.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('agileHealthApp.taskBookAppointment.local')" for="task-book-appointment-local"
                >Local</label
              >
              <input
                type="text"
                class="form-control"
                name="local"
                id="task-book-appointment-local"
                data-cy="local"
                :class="{
                  valid: !$v.taskContext.consultProcess.consult.local.$invalid,
                  invalid: $v.taskContext.consultProcess.consult.local.$invalid,
                }"
                v-model="$v.taskContext.consultProcess.consult.local.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('agileHealthApp.taskBookAppointment.date')" for="task-book-appointment-date"
                >Date</label
              >
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="task-book-appointment-date"
                    v-model="$v.taskContext.consultProcess.consult.date.$model"
                    name="date"
                    class="form-control"
                    :locale="currentLanguage"
                    button-only
                    today-button
                    reset-button
                    close-button
                  >
                  </b-form-datepicker>
                </b-input-group-prepend>

                <b-form-input
                  id="task-book-appointment-date"
                  data-cy="date"
                  type="text"
                  class="form-control"
                  name="date"
                  :class="{
                    valid: !$v.taskContext.consultProcess.consult.date.$invalid,
                    invalid: $v.taskContext.consultProcess.consult.date.$invalid,
                  }"
                  v-model="$v.taskContext.consultProcess.consult.date.$model"
                />
              </b-input-group>
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-book-appointment-execute.component.ts"></script>
