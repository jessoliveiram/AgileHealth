<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="agileHealthApp.doctor.home.createOrEditLabel"
          data-cy="DoctorCreateUpdateHeading"
          v-text="$t('agileHealthApp.doctor.home.createOrEditLabel')"
        >
          Create or edit a Doctor
        </h2>
        <div>
          <div class="form-group" v-if="doctor.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="doctor.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('agileHealthApp.doctor.name')" for="doctor-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="doctor-name"
              data-cy="name"
              :class="{ valid: !$v.doctor.name.$invalid, invalid: $v.doctor.name.$invalid }"
              v-model="$v.doctor.name.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.doctor.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./doctor-update.component.ts"></script>
