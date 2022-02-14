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
