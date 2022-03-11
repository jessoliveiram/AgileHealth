<template>
  <div>
    <h2 id="page-heading" data-cy="DoctorHeading">
      <span v-text="$t('agileHealthApp.doctor.home.title')" id="doctor-heading">Doctors</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('agileHealthApp.doctor.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'DoctorCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-doctor"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('agileHealthApp.doctor.home.createLabel')"> Create a new Doctor </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && doctors && doctors.length === 0">
      <span v-text="$t('agileHealthApp.doctor.home.notFound')">No doctors found</span>
    </div>
    <div class="table-responsive" v-if="doctors && doctors.length > 0">
      <table class="table table-striped" aria-describedby="doctors">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('agileHealthApp.doctor.name')">Name</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="doctor in doctors" :key="doctor.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DoctorView', params: { doctorId: doctor.id } }">{{ doctor.id }}</router-link>
            </td>
            <td>{{ doctor.name }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'DoctorView', params: { doctorId: doctor.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'DoctorEdit', params: { doctorId: doctor.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(doctor)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="agileHealthApp.doctor.delete.question" data-cy="doctorDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-doctor-heading" v-text="$t('agileHealthApp.doctor.delete.question', { id: removeId })">
          Are you sure you want to delete this Doctor?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-doctor"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeDoctor()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./doctor.component.ts"></script>
