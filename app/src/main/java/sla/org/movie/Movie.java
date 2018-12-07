package sla.org.movie;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    private String[] id = new String[10];
    private String[] title = new String[10];
    private String[] rating = new String[10];
    private String[] genre = new String[10];
    private String[] runTime = new String[10];
    private String[] synoposis = new String[10];
    private String[] image = new String[10];

    private String api_key = "c16cb2f114a9e49c24942d6f9590e531";
    private String api_url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + api_key + "&language=en-US&page=1";

    private Context publicContext;

    Movie(Context context) {
        publicContext = context;

        RequestQueue queue = Volley.newRequestQueue(publicContext);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject res = new JSONObject(response);
                            JSONArray obj = res.getJSONArray("results");
                            JSONObject content = obj.getJSONObject(0);
                            id[0] = content.getString("id");
                            title[0] = content.getString("title");

                            JSONArray genreOBJ = content.getJSONArray("genre_ids");
                            String genreId = genreOBJ.getString(0);
                            genre[0] = getGenre(genreId);

                            getRuntime(id[0]);
                            getRating(id[0]);

                            synoposis[0] = content.getString("overview");
                            image[0] = "https://image.tmdb.org/t/p/original" + content.getString("poster_path");

                            System.out.println(title[0]);
                            System.out.println(genre[0]);
                            System.out.println(synoposis[0]);
                            System.out.println(image[0]);

                        } catch (JSONException error) {
                            error.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void getRating(final String id) {
        RequestQueue queue = Volley.newRequestQueue(publicContext);
        final String rating_url = "https://api.themoviedb.org/3/movie/" + id + "/release_dates?api_key=" + api_key + "&language=en-US";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, rating_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject res = new JSONObject(response);
                            JSONArray obj = res.getJSONArray("results");
                            JSONObject content = obj.getJSONObject(0);
                            JSONArray releaseArray = content.getJSONArray("release_dates");
                            JSONObject typeContent = releaseArray.getJSONObject(0);
                            rating[0] = getRate(typeContent.getString("type"));
                            System.out.println(rating[0]);
                        } catch (JSONException error) {
                            error.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });



        queue.add(stringRequest);
    }

    void getRuntime(final String id) { // Minutes to hours
        RequestQueue queue = Volley.newRequestQueue(publicContext);
        final String runTime_url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + api_key + "&language=en-US&page=1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, runTime_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject res = new JSONObject(response);
                            runTime[0] = res.getString("runtime");
                            System.out.println(runTime[0]);
                        } catch (JSONException error) {
                            error.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);
    }

    String getRate(String id) {
        if (id.equals("0")) {
            return "NR";
        } else if (id.equals("1")) {
            return "G";
        }  else if (id.equals("2")) {
            return "PG";
        } else if (id.equals("3")) {
            return "PG-13";
        } else if (id.equals("4")) {
            return "R";
        } else if (id.equals("5")) {
            return "NC-17";
        } else {
            return "Unknown";
        }
    }

    String getGenre(String id) {
        if (id.equals("28")) {
            return "Action";
        } else if (id.equals("12")) {
            return "Adventure";
        } else if (id.equals("16")) {
            return "Animation";
        } else if (id.equals("35")) {
            return "Comedy";
        } else if (id.equals("80")) {
            return "Crime";
        } else if (id.equals("99")) {
            return "Documentary";
        } else if (id.equals("18")) {
            return "Drama";
        } else if (id.equals("10751")) {
            return "Family";
        } else if (id.equals("14")) {
            return "Fantasy";
        } else if (id.equals("36")) {
            return "History";
        } else if (id.equals("27")) {
            return "Horror";
        } else if (id.equals("10751")) {
            return "Family";
        } else if (id.equals("14")) {
            return "Fantasy";
        } else if (id.equals("36")) {
            return "History";
        } else if (id.equals("27")) {
            return "Horror";
        } else if (id.equals("10402")) {
            return "Music";
        } else if (id.equals("9648")) {
            return "Mystery";
        } else if (id.equals("10749")) {
            return "Romance";
        } else if (id.equals("878")) {
            return "Sci-Fi";
        } else if (id.equals("10770")) {
            return "TV Movie";
        } else if (id.equals("53")) {
            return "Thriller";
        } else if (id.equals("10752")) {
            return "War";
        } else if (id.equals("37")) {
            return "Western";
        } else {
            return "Unknown";
        }
    }

    String title(int item) {
        return title[item];
    }

    String rating(int item) { // Todo: Separate them
        return rating[item];
    }

    String genre(int item) { // Todo: Separate them
        return genre[item];
    }

    String runTime(int item) { // Todo: Separate them
        return runTime[item];
    }

    String description(int item) {
        return synoposis[item];
    }

    String image(int item) {
        return image[item]; // Need to figure out how this works.
    }

}
