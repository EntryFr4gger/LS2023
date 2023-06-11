## Modeling the database

### Data storages

Our project has two available ways to store data, a datamem, mainly used for testing, since it only works during the
apps runtime, and a Database in PSQL. In the following pages we focus on the PSQL database, since it's the most
important one, and the Datamem basically only follows what it does.

### Conceptual model ###

The following diagram holds the Entity-Relationship model for the information managed by the system.

![EA Model](https://github.com/isel-leic-ls/2223-2-LEIC41N-G01/blob/main/docs/images/EAModel.png?raw=true)
We highlight the following aspects:

* Cards have a weak relationship with a list because, although a card always has to be part of a board, it doesn't
  always
  need to be in a list.
* Since both cards and lists can only be in one board, they have a one-to-many relationship

The conceptual model has the following restrictions:

* Each board has a unique name
* Each card must belong to a list and a board, except if it is archived, in that case its list id is null
* Each list must belong to a board and has a unique name within that board
* Although card can have a dueDate, it doesn't require it

### Physical Model ###

Our database scheme was
defined [here](https://github.com/isel-leic-ls/2223-2-LEIC41N-G01/blob/main/src/main/sql/createSchema.sql)

![Physical Model](https://github.com/isel-leic-ls/2223-2-LEIC41N-G01/blob/main/docs/images/DBDiagram.png?raw=true)

We highlight the following aspects of this model:

* The `Cards` table has a `dueDate` column with a regular expression check to ensure it is a valid date/time string,
  which could be useful for tracking deadlines or scheduling tasks.
* The `Tokens` table is used to store only bearer tokens for users, and that is why it has only 36 chars of space
* The `Cards` table has both a `list_id` and a `board_id` because a card should belong in a list and always be part of a
  certain board