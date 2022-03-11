import axios from 'axios';
import { TaskBookAppointmentContext } from './task-book-appointment.model';

const baseApiUrl = 'api/consult-process/task-book-appointment';

export default class TaskBookAppointmentService {
  public loadContext(taskId: number): Promise<TaskBookAppointmentContext> {
    return new Promise<TaskBookAppointmentContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskBookAppointmentContext> {
    return new Promise<TaskBookAppointmentContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskBookAppointmentContext: TaskBookAppointmentContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskBookAppointmentContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
