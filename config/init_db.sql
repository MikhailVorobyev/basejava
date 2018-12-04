CREATE TABLE resume (
  uuid      CHAR(36) PRIMARY KEY NOT NULL,
  full_name text     NOT NULL
);

create table contact (
  id          SERIAL NOT NULL,
  resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        text     NOT NULL,
  value       text     NOT NULL
);

CREATE TABLE section
(
  id          SERIAL NOT NULL,
  resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        text NOT NULL,
  value       text NOT NULL
);

CREATE UNIQUE INDEX contact_uuid_type_index
  ON contact (resume_uuid, type);

CREATE UNIQUE INDEX section_uuid_type_index
  ON section (resume_uuid, type);