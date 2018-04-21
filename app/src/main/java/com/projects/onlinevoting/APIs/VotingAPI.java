package com.projects.onlinevoting.APIs;

import com.projects.onlinevoting.Models.CandidateModel;
import com.projects.onlinevoting.Models.MemberModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VotingAPI {

    @GET("/onlinevoting/api/check_member.php")
    Call<MemberModel> getMembers(@Query("fullname") String name);

    @GET("/onlinevoting/api/get_candidates.php")
    Call<CandidateModel> getCandidates();

}
