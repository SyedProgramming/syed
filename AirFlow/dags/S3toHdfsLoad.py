# airflow related
from airflow import DAG
from airflow.operators.python_operator import PythonOperator
from airflow.operators.bash_operator import BashOperator

from datetime import datetime
from datetime import timedelta

default_args = {
    'owner' : 'airflow',
    'depends_on_past': False,
    'start_date': datetime(2020, 9, 17),
    'email_on_failure': False,
    'email_on_retry': False,
    'schedule_interval': '@daily',
    'retries': 1,
    'catchup'=False
    'retry_delay': timedelta(seconds=5),
}


def sourceToS3Load():
    # code that writes our data from source to s3

def s3ToHDFSLoad(config, ds, **kwargs):
     # code that writes our data from s3 to hdfs
     # ds: the date of run of the given task.
     # kwargs: keyword arguments containing context parameters for the run.

def get_hdfs_config():
    #return HDFS configuration parameters required to store data into HDFS.
    return None


dag = DAG(
        dag_id='s3_to_hdfs_load_dag',
        description='S3 To HDFS Load',
        default_args=default_args
        )

config = get_hdfs_config()

source_to_s3 = PythonOperator(
                task_id='source_to_s3',
                python_callable=sourceToS3Load,
                dag=dag
                )

s3_to_hdfs = PythonOperator(
             task_id='s3_to_hdfs',
             python_callable=s3ToHDFSLoad,
             op_kwargs={'config':config},
             provide_context=True,
             dag=dag
             )

spark_job = BashOperator(
            task_id='spark_task_etl',
            bash_command='spark-submit --master spark://localhost:7077 spark_job.py',
            dag=dag
            )


# setting dependencies
source_to_s3 >> s3_to_hdfs >> spark_job

# for Airflow <v1.7
#spark_job.set_upstream(s3_to_hdfs)
#s3_to_hdfs.set_upstream(source_to_s3)

# alternatively using set_downstream
#source_to_s3.set_downstream(s3_to_hdfs)
#s3_to_hdfs.set_downstream(spark_job)
