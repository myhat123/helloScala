CREATE KEYSPACE finance
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};

create table brch_qry_dtl (
    acc varchar, 
    tran_date date, 
    amt decimal, 
    dr_cr_flag int, 
    rpt_sum text, 
    timestamp1 varchar,
    primary key (acc, timestamp1, dr_cr_flag)
) with gc_grace_seconds = 0;