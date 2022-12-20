import { User } from "./user";

export interface Course {
  courseId: number;
  freeSeets: number;
  participant: User[];
  courseName: String;
  hasPassword: boolean;
  password: String;
  creator :User;
}

