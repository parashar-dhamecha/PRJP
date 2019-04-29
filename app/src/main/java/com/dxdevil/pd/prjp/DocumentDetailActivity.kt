package com.dxdevil.pd.prjp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetails.DocDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocumentDetailActivity : AppCompatActivity() {

    var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_detail)


        token="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRpre3RUiN3wdIdsrq1N180MSCy6TWjRC69b0sK/LNMIiAMehYWjcQhhc3gpadVlW//A/OjbgREERhooiUtBj3Qc3bispZ0uzeoYMwXx1SAULv/lwD+6crG4AJf/zBCPXQCvwyRlI8mw9rwmLCdAJon2bxLr/5MIZwisAOPvC9BnJlAW7t38vIkXhXAB3cK7BIZM+jTIoHfZGQXHm2GeRgGOCTTq3MigXtbb3utVCNCZ1EuRkbd6G24UD8/0HJ+yTYJwJiNwcj5Q6t59kzyGezMGcVrMVde2d8kEOe5XnDkv/Wn5JPXqud+6WL4Z/hbvo6IJpUnatlNZD1p7fFHFecG6ChWnpVCNov+BPxL/k8+ROCb1zSMHPTr4c7TD2PNtp8LwDFQysvGbPThyD9ZFd/3ZB8m1EtXbOImhDNRxEUHGHnwrTObIY1gUycJ3gwCz8HsCGxY+62EnBA1rxG0TqERjQzqjaGeBqR9f4okL5KE+L5v/aGoUC4+XLEYsdsCjdn0VHtC7JEOK3NWcOIeuZWwEqLmJrZ7mF71HBjtjHiGIgzy5pVetEl3jEllw0F3Q99nDAYZIA1FQEeiXsUmLjDiHv/JOA2Ace7Oxybofke7dJc+X5hXJvSc6j5PgdwRA9+BP27ohQdBXADrh45RxeNbzdIVIlhRJ7Qqu+yBFucqQy5whh2ryEU0QEpDBOCeVIUhIceiUNB/oFf65TqohBfR+E8szzLkNXV1em0k6e5JZXXGBYQDXAng+3ps9mA4eRQ/hH2fR2hmjhOT+opPCg/DXt6IjQ+6L9mow5I1VdVopDbyO41vXjenrkP4H6xTXD2a58HJEd58T8XeVNLjI6+d0KRnwqpas7hY5UuoB6xLnNLMZ.xer4AsDW9pfxhxYhcsgT0rDVOxol5skggGOhn7BNQsU"

        val api = RetrofitClient.getInstance().api as Api

        val call = api.docdetails(
            token,"92832b2f-872b-408d-94ec-017c3b333acc"

        )as Call<DocDetailsResponse>

        try{
          call.enqueue(object : Callback<DocDetailsResponse> {
            override fun onFailure(call: Call<DocDetailsResponse>, t: Throwable) {
               Toast.makeText(this@DocumentDetailActivity,"Something went wrong.",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DocDetailsResponse>, response: Response<DocDetailsResponse>) {

                if(response.isSuccessful)
                    Toast.makeText(this@DocumentDetailActivity,"successful.",Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this@DocumentDetailActivity,"failure",Toast.LENGTH_SHORT).show()
               }


        }) }catch (e:Exception){
                  Toast.makeText(this@DocumentDetailActivity,"Excetion",Toast.LENGTH_SHORT).show()
              }
    }
}
