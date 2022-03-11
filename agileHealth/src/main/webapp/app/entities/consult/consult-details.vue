<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="consult">
        <h2 class="jh-entity-heading" data-cy="consultDetailsHeading">
          <span v-text="$t('agileHealthApp.consult.detail.title')">Consult</span> {{ consult.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('agileHealthApp.consult.mode')">Mode</span>
          </dt>
          <dd>
            <span>{{ consult.mode }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileHealthApp.consult.medicalSpecialty')">Medical Specialty</span>
          </dt>
          <dd>
            <span>{{ consult.medicalSpecialty }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileHealthApp.consult.local')">Local</span>
          </dt>
          <dd>
            <span>{{ consult.local }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileHealthApp.consult.date')">Date</span>
          </dt>
          <dd>
            <span>{{ consult.date }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileHealthApp.consult.doctor')">Doctor</span>
          </dt>
          <dd>
            <div v-if="consult.doctor">
              <router-link :to="{ name: 'DoctorView', params: { doctorId: consult.doctor.id } }">{{ consult.doctor.name }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./consult-details.component.ts"></script>
