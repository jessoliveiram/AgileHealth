<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="consultProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="consultProcessDetailsHeading">
          <span v-text="$t('agileHealthApp.consultProcess.detail.title')">ConsultProcess</span> {{ consultProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="consultProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('agileHealthApp.consultProcess.detail.title')">ConsultProcess</span> #{{ consultProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="consultProcess.consult">
              <h5 class="card-header">
                <span class="title"> Consult #{{ consultProcess.consult.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileHealthApp.consultProcess.mode')">mode</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="mode"
                    id="consult-mode"
                    data-cy="mode"
                    v-model="consultProcess.consult.mode"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileHealthApp.consultProcess.medicalSpecialty')">medicalSpecialty</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="medicalSpecialty"
                    id="consult-medicalSpecialty"
                    data-cy="medicalSpecialty"
                    v-model="consultProcess.consult.medicalSpecialty"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileHealthApp.consultProcess.local')">local</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="local"
                    id="consult-local"
                    data-cy="local"
                    v-model="consultProcess.consult.local"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileHealthApp.consultProcess.date')">date</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="date"
                    id="consult-date"
                    data-cy="date"
                    v-model="consultProcess.consult.date"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileHealthApp.consultProcess.doctor')" for="consult-process-doctor"
                    >Doctor</label
                  >
                  <input
                    v-if="consultProcess.consult.doctor"
                    readonly
                    type="text"
                    class="form-control"
                    name="doctor"
                    id="consult-doctor"
                    data-cy="doctor"
                    :value="consultProcess.consult.doctor.name"
                  />
                  <input v-else readonly type="text" class="form-control" name="doctor" id="consult-doctor" data-cy="doctor" value="" />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./consult-process-details.component.ts"></script>
