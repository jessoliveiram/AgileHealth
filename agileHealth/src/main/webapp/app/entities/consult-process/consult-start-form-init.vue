<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="agileHealthApp.consultStartForm.home.createOrEditLabel"
          data-cy="ConsultStartFormCreateUpdateHeading"
          v-text="$t('agileHealthApp.consultStartForm.home.createOrEditLabel')"
        >
          Create or edit a ConsultStartForm
        </h2>
        <div v-if="consultProcess.processInstance">
          <akip-show-process-definition :processDefinition="consultProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="consultProcess.consult">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileHealthApp.consultStartForm.mode')" for="consult-start-form-mode"
                    >Mode</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="mode"
                    id="consult-start-form-mode"
                    data-cy="mode"
                    :class="{ valid: !$v.consultProcess.consult.mode.$invalid, invalid: $v.consultProcess.consult.mode.$invalid }"
                    v-model="$v.consultProcess.consult.mode.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('agileHealthApp.consultStartForm.medicalSpecialty')"
                    for="consult-start-form-medicalSpecialty"
                    >Medical Specialty</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="medicalSpecialty"
                    id="consult-start-form-medicalSpecialty"
                    data-cy="medicalSpecialty"
                    :class="{
                      valid: !$v.consultProcess.consult.medicalSpecialty.$invalid,
                      invalid: $v.consultProcess.consult.medicalSpecialty.$invalid,
                    }"
                    v-model="$v.consultProcess.consult.medicalSpecialty.$model"
                  />
                </div>
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileHealthApp.consultStartForm.local')" for="consult-start-form-local"
                    >Local</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="local"
                    id="consult-start-form-local"
                    data-cy="local"
                    :class="{ valid: !$v.consultProcess.consult.local.$invalid, invalid: $v.consultProcess.consult.local.$invalid }"
                    v-model="$v.consultProcess.consult.local.$model"
                  />
                </div>
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileHealthApp.consultStartForm.date')" for="consult-start-form-date"
                    >Date</label
                  >
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-form-datepicker
                        aria-controls="consult-start-form-date"
                        v-model="$v.consultProcess.consult.date.$model"
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
                      id="consult-start-form-date"
                      data-cy="date"
                      type="text"
                      class="form-control"
                      name="date"
                      :class="{ valid: !$v.consultProcess.consult.date.$invalid, invalid: $v.consultProcess.consult.date.$invalid }"
                      v-model="$v.consultProcess.consult.date.$model"
                    />
                  </b-input-group>
                </div>
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileHealthApp.consultStartForm.doctor')" for="consult-start-form-doctor"
                    >Doctor</label
                  >
                  <select
                    class="form-control"
                    id="consult-start-form-doctor"
                    data-cy="doctor"
                    name="doctor"
                    v-model="consultProcess.consult.doctor"
                  >
                    <option v-bind:value="null"></option>
                    <option
                      v-bind:value="
                        consultProcess.consult.doctor && doctorOption.id === consultProcess.consult.doctor.id
                          ? consultProcess.consult.doctor
                          : doctorOption
                      "
                      v-for="doctorOption in doctors"
                      :key="doctorOption.id"
                    >
                      {{ doctorOption.name }}
                    </option>
                  </select>
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.consultProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./consult-start-form-init.component.ts"></script>
