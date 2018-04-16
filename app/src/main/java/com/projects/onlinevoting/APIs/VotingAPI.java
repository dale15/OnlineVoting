package com.projects.onlinevoting.APIs;

import com.projects.onlinevoting.Models.MemberModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VotingAPI {

    @GET("/check_member.php")
    Call<MemberModel> getMembers();

}
