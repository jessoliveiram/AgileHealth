<template>
  <div>
    <h2 id="page-heading" data-cy="ConsultHeading">
      <span v-text="$t('agileHealthApp.consult.home.title')" id="consult-heading">Consults</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('agileHealthApp.consult.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && consults && consults.length === 0">
      <span v-text="$t('agileHealthApp.consult.home.notFound')">No consults found</span>
    </div>
    <div class="table-responsive" v-if="consults && consults.length > 0">
      <table class="table table-striped" aria-describedby="consults">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('agileHealthApp.consult.mode')">Mode</span></th>
            <th scope="row"><span v-text="$t('agileHealthApp.consult.medicalSpecialty')">Medical Specialty</span></th>
            <th scope="row"><span v-text="$t('agileHealthApp.consult.local')">Local</span></th>
            <th scope="row"><span v-text="$t('agileHealthApp.consult.date')">Date</span></th>
            <th scope="row"><span v-text="$t('agileHealthApp.consult.doctor')">Doctor</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="consult in consults" :key="consult.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ConsultView', params: { consultId: consult.id } }">{{ consult.id }}</router-link>
            </td>
            <td>{{ consult.mode }}</td>
            <td>{{ consult.medicalSpecialty }}</td>
            <td>{{ consult.local }}</td>
            <td>{{ consult.date }}</td>
            <td>
              <div v-if="consult.doctor">
                <router-link :to="{ name: 'DoctorView', params: { doctorId: consult.doctor.id } }">{{ consult.doctor.name }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ConsultView', params: { consultId: consult.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="agileHealthApp.consult.delete.question" data-cy="consultDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-consult-heading" v-text="$t('agileHealthApp.consult.delete.question', { id: removeId })">
          Are you sure you want to delete this Consult?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-consult"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeConsult()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./consult.component.ts"></script>
