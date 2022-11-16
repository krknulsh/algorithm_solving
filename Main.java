import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int j;
	static int min = Integer.MAX_VALUE;
	static int dis[][][];
	static int poss[][];
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] jr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] jc = { 1, 2, 2, 1, -1, -2, -2, -1 };

	static class pair {
		int r;
		int c;
		int j;

		public pair(int r, int c, int j) {
			this.r = r;
			this.c = c;
			this.j = j;
		}
	}

	public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		j = Integer.parseInt(br.readLine());
		stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());

		poss = new int[n][m];
		dis = new int[n][m][j+1];

		for (int i = 0; i < n; i++) 
			for (int v = 0; v < m; v++) 
				for(int k = 0; k < j; k++)		
					dis[i][v][k] = -1;

		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) 
				poss[i][j] = Integer.parseInt(stk.nextToken());
		}

		dis[0][0][j] = 0;

		Queue<pair> q = new LinkedList<>();

		q.offer(new pair(0, 0, j));

		while (!q.isEmpty()) {
			pair tp = q.poll();

			if (tp.j > 0) {
				for (int i = 0; i < 8; i++) {
					int nr = tp.r + jr[i];
					int nc = tp.c + jc[i];

					if (nr < 0 || nr >= n || nc < 0 || nc >= m)
						continue;
					if (poss[nr][nc] == 1 || dis[nr][nc][tp.j-1] != -1)
						continue;

					dis[nr][nc][tp.j-1] = dis[tp.r][tp.c][tp.j] + 1;
					q.offer(new pair(nr, nc, tp.j - 1));
				}
			}

			for (int i = 0; i < 4; i++) {
				int nr = tp.r + dr[i];
				int nc = tp.c + dc[i];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m)
					continue;
				if (poss[nr][nc] == 1 || dis[nr][nc][tp.j] != -1)
					continue;
				dis[nr][nc][tp.j] = dis[tp.r][tp.c][tp.j] + 1;
				q.offer(new pair(nr, nc, tp.j));
			}
		}
		System.out.print(dis[n-1][m-1][0]);
	}
}